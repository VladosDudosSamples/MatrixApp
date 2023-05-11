package com.example.matrixapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.matrixapp.R
import com.example.matrixapp.model.PrivacyModel

class PrivacyViewModel : ViewModel() {
    fun getPrivacyRules(): List<PrivacyModel> {
        return listOf(
            PrivacyModel(
                "Privacy policy",
                "Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit,\nsed do eiusmod tempor incididunt ut\nlabore et dolore magna aliqua"
            ),
            PrivacyModel(
                "Privacy policy",
                "Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit,\nsed do eiusmod tempor incididunt ut\nlabore et dolore magna aliqua"
            ),
            PrivacyModel(
                "Privacy policy",
                "Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit,\nsed do eiusmod tempor incididunt ut\nlabore et dolore magna aliqua"
            ),
            PrivacyModel(
                "Privacy policy",
                "Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit,\nsed do eiusmod tempor incididunt ut\nlabore et dolore magna aliqua"
            ),
            PrivacyModel(
                "Privacy policy",
                "Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit,\nsed do eiusmod tempor incididunt ut\nlabore et dolore magna aliqua"
            ),
            PrivacyModel(
                "Privacy policy",
                "Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit,\nsed do eiusmod tempor incididunt ut\nlabore et dolore magna aliqua"
            ),
            PrivacyModel(
                "Privacy policy",
                "Lorem ipsum dolor sit amet,\nconsectetur adipiscing elit,\nsed do eiusmod tempor incididunt ut\nlabore et dolore magna aliqua"
            )
        )
    }
}