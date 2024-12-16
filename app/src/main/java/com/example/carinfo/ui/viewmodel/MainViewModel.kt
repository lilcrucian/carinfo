package com.example.carinfo.viewmodel

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _carInfo = MutableStateFlow("Введите номер автомобиля и нажмите Поиск")
    val carInfo: StateFlow<String> = _carInfo

    private var webView: WebView? = null

    fun initializeWebView(webView: WebView) {
        this.webView = webView
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
    }

    fun searchCarInfo(carNumber: String) {
        if (webView == null) {
            _carInfo.value = "Ошибка: WebView не инициализирован"
            return
        }
        _carInfo.value = "Поиск информации..."
        webView?.apply {
            loadUrl("https://avtocod.ru/")
            evaluateJavascript(
                """
                document.querySelector("#check-head > div > div:nth-child(2) > div > div > input").value = "$carNumber";
                document.querySelector("#check-head > div > div:nth-child(2) > div > button").click();
                setTimeout(() => {
                    let result = document.querySelector("#names > div > div > div > div.report-header__info > div.names-content > div.report-header__title > div > div.js-refresh__loaded > h1");
                    result ? result.innerText : "Информация не найдена";
                }, 3000);
                """.trimIndent()
            ) { result ->
                _carInfo.value = result.replace("\"", "")
            }
        }
    }
}