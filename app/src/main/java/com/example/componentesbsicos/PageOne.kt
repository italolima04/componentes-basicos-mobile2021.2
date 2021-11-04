package com.example.componentesbsicos

import android.R

import android.os.Bundle

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment


class PageOne : Fragment() {
    private var mParam1: String? = null
    private var mParam2: String? = null
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments?.getString(ARG_PARAM1)
            mParam2 = arguments?.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(com.example.componentesbsicos.R.layout.activity_main, container, false)
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        fun newInstance(param1: String?, param2: String?): PageOne {
            val fragment = PageOne()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}