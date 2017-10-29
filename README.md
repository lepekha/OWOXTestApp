# OWOXTestApp
Задание: 
На основании API от https://unsplash.com/ реализовать Android приложение. 
При открытии отображать экран с фотографиями (метод /photos) и строку для поиска (метод /search/photos) с возможностью поиска. 
При клике на фотографию открывать новый экран с фотографией на весь экран с двумя кнопками "Поделиться" и "Скачать".

Приложение было реальзовано в Материальном стиле с использованием внедрения зависимостей на основе библиотеки Dagger 2. Запросы к API https://unsplash.com/ выполняются с использование библиотеки Retrofit 2 в связке с RxAndroid и GSON для передачи и преобразования Json ответа в список обьектов. Передача списка обьектов выполняется при помощи EventBus,а загрузка изображений выполняется с использование библиотеки Picasso.
Ссылка на .apk файл:
Скриншоты:
![Скриншот_1](https://github.com/lepekha/Student_schedule/blob/master/screen_1.png)
![Скриншот_2](https://github.com/lepekha/OWOXTestApp/blob/master/screen_2.png)
