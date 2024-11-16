package com.example.roomtest

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.roomtest.data.database.AppDatabase
import com.example.roomtest.data.entity.User
import com.example.roomtest.databinding.ActivityMainBinding
import kotlin.concurrent.thread

private const val TAG = "RoomTest"

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Jack", "Ma", 40)
        val user2 = User("Tom", "Hanks", 63)

        binding.addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }

        binding.updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }

        binding.deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Ma")
            }
        }

        binding.queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d(TAG, user.toString())
                }
            }
        }

    }

}