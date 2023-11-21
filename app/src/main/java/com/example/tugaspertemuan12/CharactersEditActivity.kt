package com.example.tugaspertemuan12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tugaspertemuan12.databinding.ActivityCharactersEditBinding

class CharactersEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCharactersEditBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val data = intent.extras

        with(binding){
            EditName.setText(data?.getString("NAME"))
            EditClass.setText(data?.getString("CLASS"))
            EditHealth.setText(data?.getInt("HP").toString())
            EditMana.setText(data?.getInt("MANA").toString())
            EditWeapon.setText(data?.getString("WEAPON"))

            EditButton.setOnClickListener{
                EditNameContainer.error = null
                EditClassContainer.error = null
                EditHealthContainer.error = null
                EditManaContainer.error = null
                EditWeaponContainer.error = null
                if (EditName.text?.isEmpty() == true){
                    EditNameContainer.error = "Your hero will need a name"
                } else if (EditClass.text?.isEmpty() == true){
                    EditClassContainer.error = "Tell us more about your hero"
                } else if (EditHealth.text?.isEmpty() == true){
                    EditHealthContainer.error = "Mighty hero have great vitality"
                } else if (EditMana.text?.isEmpty() == true){
                    EditManaContainer.error = "Does magic flow's trough your hero ?"
                } else if (EditWeapon.text?.isEmpty() == true){
                    EditWeaponContainer.error = "What does your hero use to fight ?"
                } else {
                    val bundle = Bundle()
                    if (data != null) {
                        bundle.putInt("ID", data.getInt("ID", 0))
                    }
                    bundle.putString("NAME", EditName.text.toString())
                    bundle.putString("CLASS", EditClass.text.toString())
                    bundle.putInt("HP", EditHealth.text.toString().toInt())
                    bundle.putInt("MANA", EditMana.text.toString().toInt())
                    bundle.putString("WEAPON", EditWeapon.text.toString())
                    val intent = Intent().apply { putExtras(bundle) }
                    setResult(0, intent)
                    finish()
                }
            }
        }
    }
}