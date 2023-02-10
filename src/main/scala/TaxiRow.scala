case class TaxiRow(
                    VendorID: Integer,
                    tpep_pickup_datetime: String,
                    tpep_dropoff_datetime: String,
                    passenger_count: Integer,
                    trip_distance: Double,
                    RatecodeID: Integer,
                    store_and_fwd_flag: String,
                    PULocationID: Integer,
                    DOLocationID: Integer,
                    payment_type: Integer,
                    fare_amount: Double,
                    extra: Double,
                    mta_tax: Double,
                    tip_amount: Double,
                    tolls_amount: Double,
                    improvement_surcharge: Double,
                    total_amount: Double
                  )
