package com.malenalbc.stormcomics.presentation.list

import androidx.lifecycle.MutableLiveData
import com.malenalbc.stormcomics.core.architecture.BaseViewModel
import com.malenalbc.stormcomics.core.extension.subscribeForUI
import com.malenalbc.stormcomics.data.GetCharacterUseCase
import com.malenalbc.stormcomics.data.GetComicsOfCharacterUseCase
import com.malenalbc.stormcomics.data.core.extension.addToDisposables
import com.malenalbc.stormcomics.data.core.extension.logLifecycle
import com.malenalbc.stormcomics.data.model.character.Character
import com.malenalbc.stormcomics.data.model.comic.Comic
import com.malenalbc.stormcomics.model.CharacterEntity
import com.malenalbc.stormcomics.model.ComicEntity
import com.malenalbc.stormcomics.model.DataWrapper
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val characterUseCase: GetCharacterUseCase,
    private val comicsUseCase: GetComicsOfCharacterUseCase
) : BaseViewModel() {

    var header: MutableLiveData<DataWrapper<CharacterEntity>> = MutableLiveData()
    var data: MutableLiveData<DataWrapper<List<ComicEntity>>> = MutableLiveData()

    fun loadData() {
        header()
        comics()
    }

    fun header() {
        characterUseCase.getStormInfo()
            .subscribeForUI()
            .subscribe(
                { it.either(::handleCharacterError, ::handleHeader) },
                { handleCharacterError(it) }
            )
            .addToDisposables(disposables)
    }

    private fun handleHeader(character: Character) {
        header.value = DataWrapper.Success(CharacterEntity(character))
    }

    fun comics() {
        data.value = DataWrapper.Loading()
        comicsUseCase.comics()
            .logLifecycle()
            .subscribeForUI()
            .subscribe(
                { it.either(::handleComicsError, ::handleList) },
                { handleComicsError(it) }
            )
            .addToDisposables(disposables)
    }

    private fun handleCharacterError(error: Throwable?) {
        header.value = DataWrapper.Error(error, header.value?.data)
    }

    private fun handleComicsError(error: Throwable?) {
        data.value = DataWrapper.Error(error, data.value?.data)
    }

    private fun handleList(list: List<Comic>) {
        if (list.isEmpty()) {
            data.value = DataWrapper.Empty()
        } else {
            data.value = DataWrapper.Success(list.map { ComicEntity(it) })
        }
    }
}

