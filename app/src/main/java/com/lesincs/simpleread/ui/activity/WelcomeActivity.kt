package com.lesincs.simpleread.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import cn.nekocode.rxlifecycle.RxLifecycle
import com.lesincs.simpleread.R
import com.lesincs.simpleread.mvp.jdnewsprevmvp.JDNewsPrevModel
import com.lesincs.simpleread.mvp.zhnewsprevmvp.ZHNewsPrevModel
import com.lesincs.simpleread.util.PrefUtil

class WelcomeActivity : AppCompatActivity() {

    private val MIN_STAY_TIME = 700L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wellcome)
        val handler = Handler()
        val launchTime = System.currentTimeMillis()
        val currentItem = PrefUtil.getCurrentItem()
        if (currentItem == 0) {
            ZHNewsPrevModel.getLatestNewsListObs(RxLifecycle.bind(this))
                    .subscribe({
                        val duration = System.currentTimeMillis() - launchTime
                        if (duration <= MIN_STAY_TIME) {
                            handler.postDelayed({
                                startActivity(Intent(this, MainActivity::class.java))
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                                finish()
                            }, MIN_STAY_TIME - duration)
                        } else {
                            startActivity(Intent(this, MainActivity::class.java))
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                            finish()
                        }
                    }, {
                        Toast.makeText(this, "启动程序错误", Toast.LENGTH_SHORT).show()
                        finish()
                    })
        } else {
            JDNewsPrevModel.getLatestNewsObs(RxLifecycle.bind(this))
                    .subscribe({
                        val duration = System.currentTimeMillis() - launchTime
                        if (duration <= MIN_STAY_TIME) {
                            handler.postDelayed({
                                startActivity(Intent(this, MainActivity::class.java))
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                                finish()
                            }, MIN_STAY_TIME - duration)
                        } else {
                            startActivity(Intent(this, MainActivity::class.java))
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                            finish()
                        }
                    }, {
                        Toast.makeText(this, "启动程序错误", Toast.LENGTH_SHORT).show()
                        finish()
                    })
        }
    }
}
