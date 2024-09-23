package com.plcoding.room_cmp

import App
import ServiceLocator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.plcoding.room_cmp.database.getGoalDatabase
import com.plcoding.room_cmp.database.getPeopleDatabase
import com.plcoding.room_cmp.database.getProfileDatabase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = getPeopleDatabase(applicationContext).peopleDao()
        val daoProfile = getProfileDatabase(applicationContext).profileDao()
        val daoGoals = getGoalDatabase(applicationContext).goalDao()
        ServiceLocator.profileDao = daoProfile
        ServiceLocator.goalDao = daoGoals
        ServiceLocator.peopleDao = dao
        setContent {
            App()
        }
    }
}