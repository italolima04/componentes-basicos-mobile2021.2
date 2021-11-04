package com.example.componentesbsicos

import adapters.MyAdapter
import adapters.ViewPagerAdapter
import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.*
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    var mySound: MediaPlayer ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mySound = MediaPlayer.create(this, R.raw.erro)

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager)

        val adapter = ViewPagerAdapter(this)
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2,
            object : TabLayoutMediator.OnConfigureTabCallback {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = "Tab " + (position + 1)
                }
            }).attach()

        addListenerOnButton()
        addEditText()
        addAutoCompleteTextView()
        addSpinner()
        addRadioButton()
        workWithArrays()
        longClickFun()
        addListView()
        addArrayAdapterForListView()
        addGrid()
        //Dropdown Menu:
        //showPopupMenu

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuactivity,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        
        when (item.itemId){
            R.id.about -> Toast.makeText(this,"About Selected",Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this,"Settings Selected",Toast.LENGTH_SHORT).show()
            R.id.exit -> Toast.makeText(this,"Exit Selected",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showPopupMenu(view: View) = PopupMenu(view.context, view).run {
        menuInflater.inflate(R.menu.menupopup, menu)
        setOnMenuItemClickListener { item ->
            Toast.makeText(view.context, "You Clicked : ${item.title}", Toast.LENGTH_SHORT).show()
            true
        }
        show()
    }

    private fun playSound(view: View) {
        this.mySound?.start()
    }

    private fun workWithArrays() {
        //Trabalhando com Arrays:
        val num = Array(3) { i -> i * 1 }
        println("Trabalhando com Arrays: ")
        println(num)
    }

    private fun addListenerOnButton()  {
        val toggleButton1 = findViewById(R.id.toggleButton1) as ToggleButton
        val toggleButton2 = findViewById(R.id.toggleButton2) as ToggleButton
        val btnDisplay = findViewById(R.id.btnDisplay) as Button


        btnDisplay.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                val result = StringBuffer()
                result.append("toggleButton1 : ").append(toggleButton1.text)
                result.append("\ntoggleButton2 : ").append(toggleButton2!!.text)
                Toast.makeText(
                    this@MainActivity, result.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun addEditText() {
        val showButton = findViewById<Button>(R.id.showInput)
        val editText = findViewById<EditText>(R.id.editText)

        showButton.setOnClickListener {
            val text = editText.text
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }
    }

    private fun addAutoCompleteTextView() {
        val languages = resources.getStringArray(R.array.Languages)
        val autotextView = findViewById<AutoCompleteTextView>(R.id.autoTextView)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, languages)
        autotextView.setAdapter(adapter)
    }

    private fun addSpinner() {
        val languages = resources.getStringArray(R.array.Languages)
         System.out.println(languages)
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, languages
            )
            spinner.adapter = adapter
            object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }.also { spinner.onItemSelectedListener = it }
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position],
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
    }

    private fun addRadioButton() {
        val radio_group = findViewById(R.id.radio_group) as RadioGroup
        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(applicationContext," On checked change :"+
                        " ${radio.text}",
                    Toast.LENGTH_SHORT).show()
            })
    }

    private fun radio_button_click(view: View){
        val radio_group = findViewById<RadioGroup>(R.id.radio_group)
        val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
        Toast.makeText(applicationContext,"On click : ${radio.text}",
            Toast.LENGTH_SHORT).show()
    }

    private fun longClickFun(){
        val button = findViewById<Button>(R.id.press);
        button.setOnLongClickListener(View.OnLongClickListener {
            Toast.makeText(
                applicationContext,
                "Voce está me pressionando :/", 2000
            ).show()
            true
        })
    }

    private fun addListView() {
        val arrayAdapter: ArrayAdapter<*>
        val users = arrayOf(
            "Ítalo Lima", "Marcos Gênesis", "Lucas Nascimento",
            "Fabrício Nogueira"
        )
        var mListView = findViewById<ListView>(R.id.userlist)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, users)
        mListView.adapter = arrayAdapter
    }

    private fun addArrayAdapterForListView() {
        val arrayAdapter: ArrayAdapter<*>
        val users = arrayOf(
            10, 20, 30, 40
        )

        val itemsAdapter: ArrayAdapter<Int> =
            ArrayAdapter<Int>(this, android.R.layout.simple_list_item_1, users)

        var mListView = findViewById<ListView>(R.id.userlistadapter)
        arrayAdapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, users)
        mListView.adapter = arrayAdapter
    }

    private fun addGrid() {
        lateinit var gridView: GridView
        var friends = arrayOf("Ítalo", "Marcos", "Lucas", "Fabrício")

        gridView = findViewById(R.id.grid)
        val mainAdapter = MyAdapter(this@MainActivity, friends)
        gridView.adapter = mainAdapter
        gridView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(applicationContext, "You CLicked " + friends[+position],
                Toast.LENGTH_SHORT).show()
        }
    }
}