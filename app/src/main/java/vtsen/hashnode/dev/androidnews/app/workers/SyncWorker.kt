package vtsen.hashnode.dev.androidnews.app.workers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import vtsen.hashnode.dev.androidnews.R
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepository
import vtsen.hashnode.dev.androidnews.domain.repository.ArticlesRepositoryStatus
import vtsen.hashnode.dev.androidnews.ui.MainActivity
import javax.inject.Inject

class SyncWorker(appContext: Context, params: WorkerParameters)
    : CoroutineWorker(appContext, params) {

    private val notificationChannelId = "AndroidNewsNotificationChannelId"
    @Inject lateinit var repository: ArticlesRepository


    override suspend fun doWork(): Result {
        val status = repository.refresh()

        if (status is ArticlesRepositoryStatus.Success) {

            if(status.withNewArticles) {
                with(NotificationManagerCompat.from(applicationContext)) {
                    notify(0, createNotification())
                }
            }

            return Result.success()
        }

        return Result.retry()
    }

    private fun createNotification() : Notification {
        createNotificationChannel()

        val mainActivityIntent = Intent(applicationContext, MainActivity::class.java)

        val pendingIntentFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val mainActivityPendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            mainActivityIntent,
            pendingIntentFlag)


        return NotificationCompat.Builder(applicationContext, notificationChannelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentText("New Articles Arrived!")
            .setContentIntent(mainActivityPendingIntent)
            .setAutoCancel(true)
            .build()
    }

    private fun createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel = NotificationChannel(
                notificationChannelId,
                "Sync Articles",
                NotificationManager.IMPORTANCE_DEFAULT,
            )

            val notificationManager: NotificationManager? =
                ContextCompat.getSystemService(applicationContext, NotificationManager::class.java)

            notificationManager?.createNotificationChannel(notificationChannel)
        }
    }
}