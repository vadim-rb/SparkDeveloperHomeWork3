# SparkDeveloperHomeWork3
Загрузить данные в DataSet из файла с фактическими данными поездок в Parquet (src/main/resources/data/yellow_taxi_jan_25_2018).  
С помощью DSL и lambda построить таблицу, которая покажет.  
Как происходит распределение поездок по дистанции? Результат вывести на экран и записать в бд Постгрес (докер в проекте).  
##
Запуск с параметрами
```
spark-submit --master yarn-cluster --class "Main" HomeWork3-assembly-0.1.0-SNAPSHOT.jar \
--hostname  \
--port \
--database \
--username \ 
--password \
--table \  
--parquet \ 
--csv 
```
