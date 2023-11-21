package com.example.tugaspertemuan12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugaspertemuan12.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var mCharactersDao: CharactersDao
    private lateinit var binding : ActivityMainBinding
    private lateinit var executorService: ExecutorService

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
            val data = result.data?.extras
//            Edit
            if (result.resultCode == 0 && data != null) {
                update(
                    Characters(
                        id = data.getInt("ID", 0),
                        char_name = data.getString("NAME").toString(),
                        char_class = data.getString("CLASS").toString(),
                        hp = data.getInt("HP", 0),
                        mana = data.getInt("MANA"),
                        char_weapon = data.getString("WEAPON").toString()
                    )
                )
            }
//            Create
            else if (result.resultCode == 1 && data != null){
                insert(
                    Characters(
                        char_name = data.getString("NAME").toString(),
                        char_class = data.getString("CLASS").toString(),
                        hp = data.getInt("HP", 0),
                        mana = data.getInt("MANA"),
                        char_weapon = data.getString("WEAPON").toString()
                    )
                )
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        executorService = Executors.newSingleThreadExecutor()
        val db = RPGDatabase.getDatabase(this)
        mCharactersDao = db!!.charactersDao()!!

        mCharactersDao.allCharacters.observe(this){
            binding.mainRecyclerView.apply {
                adapter = CharactersAdapter(it,
                    { characters ->
//                        edit
                        val bundle = Bundle()
                        bundle.putInt("ID", characters.id)
                        bundle.putString("NAME", characters.char_name)
                        bundle.putString("CLASS", characters.char_class)
                        bundle.putInt("HP", characters.hp)
                        bundle.putInt("MANA", characters.mana)
                        bundle.putString("WEAPON", characters.char_weapon)
                        val intent = Intent(this@MainActivity, CharactersEditActivity::class.java).apply { putExtras(bundle) }
                        launcher.launch(intent)
                    },
                    { characters ->
//                        delete
                        delete(characters)
                    }
                )
            }
        }
        with(binding){
            mainRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
            mainCreateButton.setOnClickListener {
                val intent = Intent(this@MainActivity, CharactersCreateActivity::class.java)
                launcher.launch(intent)
            }
        }
    }

    private fun delete(characters: Characters) {
        executorService.execute{
            mCharactersDao.delete(characters)
        }
    }

    private fun update(characters: Characters){
        executorService.execute{
            mCharactersDao.update(characters)
        }
    }

    private fun insert(characters: Characters){
        executorService.execute{
            mCharactersDao.insert(characters)
        }
    }
}