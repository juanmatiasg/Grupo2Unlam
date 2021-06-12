package com.example.navigationdrawer.domain

import com.example.navigationdrawer.data.DataSource
import com.example.navigationdrawer.data.model.ListMeals
import com.example.navigationdrawer.data.model.MealsInformation
import com.example.navigationdrawer.vo.Resource

class RepoImp(private val dataSource: DataSource):Repo{

    override suspend fun getListMeals(term: String): Resource<ListMeals> {
        return dataSource.getListMeals(term)
    }

    override suspend fun getMealsInformation(id: String): Resource<MealsInformation> {
        return dataSource.getMealsInformation(id)
    }

}