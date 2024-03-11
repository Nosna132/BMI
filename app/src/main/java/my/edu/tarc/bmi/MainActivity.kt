package my.edu.tarc.bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.edu.tarc.bmi.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    //module level
    //step1: create an instance of the view binding class
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //step2: link binding object to UI
        binding = ActivityMainBinding.inflate(layoutInflater)
        //step3: set the root of the binding as the UI
        setContentView(binding.root)

        //from this point onwards - use the binding class to access UI components
        binding.buttonCalculate.setOnClickListener{
            //input validation
            if(binding.editTextWeight.text.isEmpty()){
                binding.editTextWeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }
            if (binding.editTextHeight.text.isEmpty()){
                binding.editTextHeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }

            //calculate bmi
            val weight = binding.editTextWeight.text.toString().toFloat()
            val height = binding.editTextHeight.text.toString().toFloat()
            val bmi = weight/(height/100).pow(2)

            //display outputs
            if(bmi < 18.5) {
                binding.textViewBMI.text = String.format("%s %.2f (%s)", getString(R.string.bmi), bmi, getString(R.string.underweight))
                binding.imageBMI.setImageResource(R.drawable.under)
            }else if(bmi > 18.5 && bmi < 24.9){
                binding.textViewBMI.text = String.format("%s %.2f (%s)", getString(R.string.bmi), bmi, getString(R.string.normal))
                binding.imageBMI.setImageResource(R.drawable.normal)
            }else if(bmi > 24.9){
                binding.textViewBMI.text = String.format("%s %.2f (%s)", getString(R.string.bmi), bmi, getString(R.string.overweight))
                binding.imageBMI.setImageResource(R.drawable.over)
            }
            binding.buttonReset.setOnClickListener{

            }
            binding.imageViewMoreInfo.setOnClickListener{
                //create an instance of intent class
                val intent = Intent(this, MoreInfoActivity::class.java)
                startActivity(intent)
            }
        }
    }
}