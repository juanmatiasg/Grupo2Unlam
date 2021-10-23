package com.example.navigationdrawer.di

import android.app.Application
import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.database.AppDataBase
import com.example.navigationdrawer.domain.Repo
import com.example.navigationdrawer.domain.RepoImp
import com.example.navigationdrawer.ui.favourite.FavouriteViewModel
import com.example.navigationdrawer.ui.home.HomeViewModel
import com.example.navigationdrawer.ui.meal.MealViewModel
import com.example.navigationdrawer.ui.mealDetail.MealDetailViewModel
import com.example.navigationdrawer.ui.registrer.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application(){

    val apiModule = module {


        single{DataSource(AppDataBase.getDatabase(get()))}
        single<Repo>{RepoImp(get())}
        viewModel { MealViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel {MealDetailViewModel(get())}
        viewModel { FavouriteViewModel(get()) }
        viewModel { RegisterViewModel(get()) }

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