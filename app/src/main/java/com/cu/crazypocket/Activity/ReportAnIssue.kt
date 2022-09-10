package com.cu.crazypocket.Activity

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cu.crazypocket.R
import com.cu.crazypocket.databinding.ActivityReportAnIssueBinding

class ReportAnIssue : AppCompatActivity() {
    lateinit var binding: ActivityReportAnIssueBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_report_an_issue)
        binding.submit.setOnClickListener(View.OnClickListener {
            val dialog = AlertDialog.Builder(this@ReportAnIssue)
            val mView = layoutInflater.inflate(R.layout.dialog, null)
            val dialogButton = mView.findViewById<View>(R.id.okay) as Button
            dialog.setView(mView)
            val alertDialog = dialog.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialog.setCanceledOnTouchOutside(false)
            dialogButton.setOnClickListener { alertDialog.dismiss() }
            alertDialog.show()
        })
    }
}
