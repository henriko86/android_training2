package com.yuruneji.myapplication.presentation

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.AppLaunchChecker
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.yuruneji.myapplication.R
import com.yuruneji.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private var fabButtonClick: FabButtonClick? = null

    private lateinit var mService: MyService
    private var mBound: Boolean = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance.
            val binder = service as MyService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.i(Throwable().stackTrace[0].methodName)
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // binding.fab.setOnClickListener { view ->
        //     // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //     //     .setAction("Action", null)
        //     //     .setAnchorView(R.id.fab).show()
        //
        //     fabButtonClick?.onFabClicked()
        // }

        val before = AppLaunchChecker.hasStartedFromLauncher(applicationContext)
        Timber.d("before:$before")
        AppLaunchChecker.onActivityCreate(this)
        val after = AppLaunchChecker.hasStartedFromLauncher(applicationContext)
        Timber.d("after:$after")

        val serviceIntent = Intent(this, MyService::class.java)
        startForegroundService(serviceIntent)

        // val wokManager = WorkManager.getInstance(this)
        // wokManager.cancelAllWork()
        // wokManager.enqueue(
        //     // PeriodicWorkRequest.Builder(
        //     //     LogUploadWorker::class.java,
        //     //     15,
        //     //     TimeUnit.MINUTES
        //     // ).build()
        // )
    }

    override fun onDestroy() {
        Timber.i(Throwable().stackTrace[0].methodName)
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        Timber.i(Throwable().stackTrace[0].methodName)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun setFabButtonClickListener(fabButtonClick: FabButtonClick) {
        Timber.i(Throwable().stackTrace[0].methodName)
        this.fabButtonClick = fabButtonClick
    }

    // fun checkAndRequestAlarmPermission(context: Context) {
    //     Timber.i(Throwable().stackTrace[0].methodName)
    //     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
    //         val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    //         if (!alarmManager.canScheduleExactAlarms()) {
    //             val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
    //             context.startActivity(intent)
    //         }
    //     }
    // }
}
