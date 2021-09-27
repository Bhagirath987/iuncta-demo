package com.demo.mobile.app.ui.main.detail

import android.content.Context
import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.demo.mobile.app.R
import com.demo.mobile.app.data.beans.GetRequestData
import com.demo.mobile.app.databinding.ActivityDetailBinding
import com.demo.mobile.app.di.base.view.AppActivity
import java.util.Objects.isNull

class DetailActivity : AppActivity<ActivityDetailBinding, DetailActivityVM>() {
    private var getData: GetRequestData = GetRequestData()
    private var type: Int = 0

    fun newIntent(context: Context, data: GetRequestData?, type: Int): Intent {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("data", data)
        intent.putExtra("type", type)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        return intent
    }

    fun newIntent(context: Context, type: Int): Intent {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("type", type)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        return intent
    }

    override fun getBindingActivity(): BindingActivity<DetailActivityVM> {
        return BindingActivity(R.layout.activity_detail, DetailActivityVM::class.java)
    }

    override fun subscribeToEvents(vm: DetailActivityVM) {
        if (intent.hasExtra("data")) {
            getData = intent.getSerializableExtra("data") as GetRequestData
            binding.tvFullName.text = getData.fullName
            binding.tvBirthdate.text = "Birthdate : " + getData.birthdate
            binding.tvEmail.text = "Email : " + getData.email
            binding.tvPhone.text = "Phone : " + getData.phone_number
            binding.tvAddress.text = "Address : " + getData.address
            if (getData.gender == "0") {
                binding.tvGender.text = "Male"
            } else if (getData.gender == "1") {
                binding.tvGender.text = "FeMale"
            } else {
                binding.tvGender.text = "UnSpecify"
            }
            if (!isNull(getData.website)) {
                binding.tvWebsite.text = "Website : ${getData.website}"
            } else {
                binding.tvWebsite.text = "Website : "
            }

            binding.tvCompany.text = "Company : " + getData.company_name
            binding.tvPosition.text = "Position : " + getData.position
            Glide.with(this).load(getData.profile_picture).into(binding.imgProfile)


        }
        type = intent.getIntExtra("type", 0)
        if (type == 1) {
            binding.tvFullName.visibility = View.VISIBLE
            binding.tvBirthdate.visibility = View.VISIBLE
            binding.tvEmail.visibility = View.VISIBLE
            binding.tvPhone.visibility = View.VISIBLE
            binding.tvAddress.visibility = View.VISIBLE
            binding.tvGender.visibility = View.VISIBLE
            binding.tvWebsite.visibility = View.VISIBLE
            binding.tvCompany.visibility = View.VISIBLE
            binding.tvPosition.visibility = View.VISIBLE
            binding.imgProfile.visibility = View.VISIBLE
            binding.tvHeading.text = resources.getString(R.string.register_successful)
        } else if (type == 5) {
            binding.tvHeading.text = resources.getString(com.demo.mobile.app.R.string.payment_succss)
            binding.tvOrderId.visibility = View.VISIBLE
            binding.tvAmount.visibility = View.VISIBLE
            binding.tvTransactionId.visibility = View.VISIBLE
            binding.tvSuccess.visibility = View.VISIBLE

            binding.tvOrderId.text = "OrderIs : " + getData.order_id.toString()
            binding.tvAmount.text = "Amount : " + getData.amount.toString()
            binding.tvTransactionId.text = "Transaction : " + getData.transaction_id.toString()
            binding.tvSuccess.text = "Success"


        } else {
            binding.tvHeading.text = resources.getString(R.string.login_successful)
        }
        vm.obrClick.observe(this, { view ->
            when (view.id) {
                R.id.tvBack -> {
                   onBackPressed()
                }
            }
        })
    }
}