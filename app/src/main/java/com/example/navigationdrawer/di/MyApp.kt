package com.example.navigationdrawer.di

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.ViewModelProvider
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.database.AppDataBase
import com.example.navigationdrawer.domain.ApiMeals
import com.example.navigationdrawer.domain.MealDao
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.ui.factory.VMFactory
import com.example.navigationdrawer.ui.home.HomeFragment
import com.example.navigationdrawer.ui.home.HomeViewModel
import com.example.navigationdrawer.ui.meal.MealViewModel
import com.example.navigationdrawer.ui.mealDetail.MealDetailViewModel
import com.example.navigationdrawer.vo.RetrofitClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApp : Application(){

    val apiModule = module {

        single{DataSource(AppDataBase.getDatabase(get()))}
        single<Repo>{RepoImp(get())}
        viewModel { MealViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel {MealDetailViewModel(get())}

    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(apiModule)
        }
    }

}