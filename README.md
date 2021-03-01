Тестовое задание
================

[![Build Status](https://travis-ci.com/nickbeam/veeam.qa.dev.hash.svg?branch=master)](https://github.com/nickbeam/veeam.qa.dev.hash)

Программа считывает исходный текстовый файл, в котором перечислены список файлов, их алгоритмы шифрования (один из `MD5`, `SHA1`, `SHA256`) и соответствующие им хеш-суммы.
В указанном пользователем каталоге производит поиск файлов, перечисленных в исходном текстовом файле, вычисляет их хэш-суммы по алгоритму, заданному в этой-же строке исходного файла.
Сравнивает вычисленную хэш-сумму с указанной в файле и выводит на экран название файла и результат сравнения `OK` или `FAIL`.
Если файл не найден выводит `NOT FOUND`.
Исходный текстовый файл и каталог, содержащий файлы для обработки, передаются в качестве параметров. 

Исходные данные:
----------------
Каждая строка исходного файла должна содержать три параметра, записанных через пробел: 
1. Имя файла
2. Алгоритм шифрования
3. Хэш-сумму (вычисленную по указанному в строке алгоритму шифрования)

Пример исходного файла:
-----------------------
```
file_01.bin md5 aaeab83fcc93cd3ab003fa8bfd8d8906
file_02.bin md5 6dc2d05c8374293fe20bc4c22c236e2e
file_03.bin md5 6dc2d05c8374293fe20bc4c22c236e2e
файл_04.txt sha1 da39a3ee5e6b4b0d3255bfef95601890afd80709
файл_04.txt sha1 dd39a3ee5e6b4b0d3255bfef95601890afd80709
файл_05.txt sha1 dd39a3ee5e6b4b0d3255bfef95601890afd80709
файл_06.txt sha256 e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855
```

Пример вызова:
--------------
`java -jar <jar file> <path to the input file> <path to the directory containing the files to check>`

Пример вывода:
--------------
```
file_01.bin FAIL
file_02.bin FAIL
file_03.bin NOT FOUND
файл_04.txt OK
файл_04.txt FAIL
файл_05.txt FAIL
файл_06.txt OK
```

Установка и запуск:
----------
1. Распакуйте архив (*распаковать здесь*) на диск `C:\`
2. Выполните команду в консоли: `java -jar c:\tmp\hash-jar-with-dependencies.jar c:\tmp\import.txt c:\tmp\files`