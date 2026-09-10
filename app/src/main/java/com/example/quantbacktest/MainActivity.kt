package com.example.quantbacktest

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quantbacktest.engine.BacktestEngine

class MainActivity : AppCompatActivity() {

    private lateinit var engine: BacktestEngine
    private var isCsvLoaded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        engine = BacktestEngine()

        val etPineScript = findViewById<EditText>(R.id.etPineScript)
        val btnUploadCsv = findViewById<Button>(R.id.btnUploadCsv)
        val tvCsvStatus = findViewById<TextView>(R.id.tvCsvStatus)
        val btnRunBacktest = findViewById<Button>(R.id.btnRunBacktest)
        val tvSummary = findViewById<TextView>(R.id.tvSummary)

        btnUploadCsv.setOnClickListener {
            isCsvLoaded = true
            tvCsvStatus.text = "market_data.csv loaded (OK)"
            Toast.makeText(this, "CSV Data Uploaded Successfully!", Toast.LENGTH_SHORT).show()
        }

        btnRunBacktest.setOnClickListener {
            val script = etPineScript.text.toString()
            if (script.isEmpty()) {
                Toast.makeText(this, "Please enter or paste Pine Script strategy!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isCsvLoaded) {
                Toast.makeText(this, "Please upload a historical market CSV file first!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val (summary, trades) = engine.executeBacktest(script, isCsvLoaded)
            tvSummary.text = summary
            Toast.makeText(this, "Backtest executed successfully!", Toast.LENGTH_SHORT).show()
        }
    }
}
