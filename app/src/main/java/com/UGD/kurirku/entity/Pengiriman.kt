package com.UGD.kurirku.entity

class Pengiriman (var kategori: String , var waktu: String, var harga: Double) {

    companion object {
        @JvmField
        var listOfPengiriman = arrayOf(
            Pengiriman("Ekspress", "One day", 45000.0),
            Pengiriman("Premiun", "two days", 40000.0),
            Pengiriman("Reguler", "three days", 35000.0),
            Pengiriman("Oke","four days", 30000.0),
            Pengiriman("Internasional", "N/A", 1000000.0),
            Pengiriman("Regional", "N/A", 500000.0),
            Pengiriman("Protected", "N/A", 250000.0),
            Pengiriman("Insured", "N/A", 50000.0),
            Pengiriman("Container", "N/A", 2500000.0),
            Pengiriman("Dropshiper", "N/A", 100000.0)
        )
    }
}