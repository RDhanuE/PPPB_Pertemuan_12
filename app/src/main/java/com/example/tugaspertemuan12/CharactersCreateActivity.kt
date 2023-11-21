package com.example.tugaspertemuan12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.tugaspertemuan12.databinding.ActivityCharactersCreateBinding

class CharactersCreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCharactersCreateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
            CreateButton.setOnClickListener{
                CreateNameContainer.error = null
                CreateClassContainer.error = null
                CreateHealthContainer.error = null
                CreateManaContainer.error = null
                CreateWeaponContainer.error = null
                if (CreateName.text?.isEmpty() == true){
                    CreateNameContainer.error = "Your hero will need a name"
                } else if (CreateClass.text?.isEmpty() == true){
                    CreateClassContainer.error = "Tell us more about your hero"
                } else if (CreateHealth.text?.isEmpty() == true){
                    CreateHealthContainer.error = "Mighty hero have great vitality"
                } else if (CreateMana.text?.isEmpty() == true){
                    CreateManaContainer.error = "Does magic flow's trough your hero ?"
                } else if (CreateWeapon.text?.isEmpty() == true){
                    CreateWeaponContainer.error = "What does your hero use to fight ?"
                } else {
                    val bundle = Bundle()
                    bundle.putString("NAME", CreateName.text.toString())
                    bundle.putString("CLASS", CreateClass.text.toString())
                    bundle.putInt("HP", CreateHealth.text.toString().toInt())
                    bundle.putInt("MANA", CreateMana.text.toString().toInt())
                    bundle.putString("WEAPON", CreateWeapon.text.toString())
                    val intent = Intent().apply { putExtras(bundle) }
                    setResult(1, intent)
                    finish()
                }
            }
        }
    }
}