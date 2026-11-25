package com.example.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private val _counter = MutableLiveData(0)          // начальное значение 0
    val counter: LiveData<Int> = _counter

    val input = MutableLiveData("")                     // двусторонняя связь с EditText

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun increment() {
        _counter.value = (_counter.value ?: 0) + 1
    }

    fun decrement() {
        _counter.value = (_counter.value ?: 0) - 1
    }

    fun apply() {
        val number = input.value?.trim()?.toIntOrNull()
        if (number == null) {
            _error.value = "Введите целое число"
            return
        }
        _counter.value = number
        input.value = ""        // очищаем поле
        _error.value = null
    }
}
