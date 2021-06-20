package com.example.spaceinfo.fragments.view_models

sealed class LoadingStrategy
object Internet : LoadingStrategy()
object LocalStorage : LoadingStrategy()
