package com.yoon.calcapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yoon.calcapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onButtonClicked()
    }

    private fun onButtonClicked() {
        binding.btn0.setOnClickListener {
            // 비어있을 때 때 0을 추가하지 않는다.
            if (!binding.tvCalc.text.isNullOrBlank()) {
                checkNumLengthAndAppend("0")
            }
        }

        binding.btn1.setOnClickListener {
            checkNumLengthAndAppend("1")
        }

        binding.btn2.setOnClickListener {
            checkNumLengthAndAppend("2")
        }

        binding.btn3.setOnClickListener {
            checkNumLengthAndAppend("3")
        }

        binding.btn4.setOnClickListener {
            checkNumLengthAndAppend("4")
        }

        binding.btn5.setOnClickListener {
            checkNumLengthAndAppend("5")
        }

        binding.btn6.setOnClickListener {
            checkNumLengthAndAppend("6")
        }

        binding.btn7.setOnClickListener {
            checkNumLengthAndAppend("7")
        }

        binding.btn8.setOnClickListener {
            checkNumLengthAndAppend("8")
        }

        binding.btn9.setOnClickListener {
            checkNumLengthAndAppend("9")
        }

        // 지우기 버튼
        binding.btnDelete.setOnClickListener {
            val text = binding.tvCalc.text
            binding.tvPreview.text = ""

            if (!text.isNullOrBlank()) {
                var hasOperator = false
                for (s in binding.tvCalc.text) {
                    if (s.toString().isOperator())
                        hasOperator = true
                }

                binding.tvCalc.text = text.substring(0, text.length - 1)

                if ( !binding.tvCalc.text.toString().isNullOrBlank() && binding.tvCalc.text.last().toString().isNumber() && hasOperator) {
                    updatePreview()

                } else if ( !binding.tvCalc.text.toString().isNullOrBlank() && binding.tvCalc.text.last().toString().isOperator()) {

                }
            } else {

            }
        }

        // AC 버튼
        binding.btnClear.setOnClickListener {
            binding.tvCalc.text = ""
            binding.tvPreview.text = ""
        }

        // 더하기 버튼 (+)
        binding.btnPlus.setOnClickListener {
            val text = binding.tvCalc.text

            if (text.isNullOrBlank()) {
                Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
            } else {
                when (text.last().toString()) {
                    "-" -> {
                        changeToPlus(text)
                    }
                    "X" -> {
                        changeToPlus(text)
                    }
                    "÷" -> {
                        changeToPlus(text)
                    }
                    "+" -> {}
                    else -> binding.tvCalc.append("+")
                }
            }
        }

        // 빼기 버튼 (-)
        binding.btnMinus.setOnClickListener {
            val text = binding.tvCalc.text

            if (text.isNullOrBlank()) {
                Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
            } else {
                when (text.last().toString()) {
                    "-" -> {}
                    "X" -> {
                        changeToMinus(text)
                    }
                    "÷" -> {
                        changeToMinus(text)
                    }
                    "+" -> {
                        changeToMinus(text)
                    }
                    else -> binding.tvCalc.append("-")
                }
            }
        }

        // 나누기 버튼
        binding.btnDiv.setOnClickListener {

            val text = binding.tvCalc.text

            if (text.isNullOrBlank()) {
                Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
            } else {
                when (text.last().toString()) {
                    "-" -> {
                        changeToDiv(text)
                    }
                    "X" -> {
                        changeToDiv(text)
                    }
                    "÷" -> {}
                    "+" -> {
                        changeToDiv(text)
                    }
                    else -> binding.tvCalc.append("÷")
                }
            }
        }

        // 곱하기 버튼(X)
        binding.btnMul.setOnClickListener {
            val text = binding.tvCalc.text

            if (text.isNullOrBlank()) {
                Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
            } else {
                when (text.last().toString()) {
                    "-" -> {
                        changeToMul(text)
                    }
                    "X" -> {}
                    "÷" -> {
                        changeToMul(text)
                    }
                    "+" -> {
                        changeToMul(text)
                    }
                    else -> binding.tvCalc.append("X")
                }
            }
        }

        // Percent 버튼(%)
        binding.btnPercent.setOnClickListener {
            val text = binding.tvCalc.text

            if (text.isNullOrBlank()) {
                Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
            } else if (text.last().toString().isNumber()) {
                binding.tvCalc.append("%")
            } else {
                Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
            }

        }

        // 소수점 버튼
        binding.btnDot.setOnClickListener {
            val text = binding.tvCalc.text

        }

        // 결과 버튼
        binding.btnResult.setOnClickListener {
            val text = binding.tvCalc.text

            if (text.isNullOrBlank()) {

            } else if (text.last().toString().isOperator()) {
                Toast.makeText(this, "완성되지 않은 수식입니다.", Toast.LENGTH_SHORT).show()
            } else if (text.last().toString()
                    .toInt() == 0 && text[text.length - 2].toString() == "÷"
            ) {
                Toast.makeText(this, "0으로 나눌 수 없어요.", Toast.LENGTH_SHORT).show()
            } else {
                // 연산자 찾기
                for (c in text) {
                    if (c.toString().isOperator()) {
                        text.split(c)
                    }
                }
                // 연산하기

                // 결과 표시하기
            }
        }

    }

    private fun checkNumLengthAndAppend(input : String ) {
        var opIdx = 0
        val text = binding.tvCalc.text.toString()
        val length = text.length
        for (idx in length - 1 downTo 0) {
            if (text[idx].toString().isOperator()) {
                opIdx = idx
                break
            }
        }

        if ( length >= opIdx+1 && text.substring(opIdx + 1, length).length >= 14  ) {
            Toast.makeText(this, "15자리까지 입력할 수 있어요.", Toast.LENGTH_SHORT).show()
        } else {
            binding.tvCalc.append(input)
            updatePreview()
        }
    }

    // 미리보기 업데이트
    private fun updatePreview() {
        val text = binding.tvCalc.text
        val preview = binding.tvPreview.text
        var op = ""
        var firstOpIdx = 0

        // 이미 가져온 값이 숫자라면 ? 새로운 연산자로 연산을 하자.
        if (preview.toString().isNumber()) {

            for (idx in text.length - 1 downTo 0) {
                if (text[idx].toString().isOperator()) {
                    op = text[idx].toString()
                    firstOpIdx = idx
                    break
                }
            }

            when (op) {
                "+" -> {
                    binding.tvPreview.text =
                        (preview.toString().toInt() + text.substring(firstOpIdx + 1, text.length)
                            .toInt()).toString()
                }
                "-" -> {
                    binding.tvPreview.text =
                        (preview.toString().toInt() - text.substring(firstOpIdx + 1, text.length)
                            .toInt()).toString()
                }
                "X" -> {
                    binding.tvPreview.text =
                        (preview.toString().toInt() * text.substring(firstOpIdx + 1, text.length)
                            .toInt()).toString()
                }
                "÷" -> {
                    binding.tvPreview.text =
                        (preview.toString().toInt() / text.substring(firstOpIdx + 1, text.length)
                            .toInt()).toString()
                }
            }
        } else {
            val text = binding.tvCalc.text
            for (s in text) {
                if (s.toString().isOperator()) {
                    val op = s.toString()
                    val nums = text.split(op)
                    when (op) {
                        "+" -> {
                            binding.tvPreview.text = (nums[0].toInt() + nums[1].toInt()).toString()
                        }
                        "-" -> {
                            binding.tvPreview.text = (nums[0].toInt() - nums[1].toInt()).toString()
                        }
                        "X" -> {
                            binding.tvPreview.text = (nums[0].toInt() * nums[1].toInt()).toString()
                        }
                        "÷" -> {
                            binding.tvPreview.text = (nums[0].toInt() / nums[1].toInt()).toString()
                        }
                    }
                }
            }


        }
    }

    private fun changeToPlus(text: CharSequence) {
        binding.tvCalc.text = text.substring(0, text.length - 1)
        binding.tvCalc.append("+")
    }

    private fun changeToMinus(text: CharSequence) {
        binding.tvCalc.text = text.substring(0, text.length - 1)
        binding.tvCalc.append("-")
    }

    private fun changeToDiv(text: CharSequence) {
        binding.tvCalc.text = text.substring(0, text.length - 1)
        binding.tvCalc.append("÷")
    }

    private fun changeToMul(text: CharSequence) {
        binding.tvCalc.text = text.substring(0, text.length - 1)
        binding.tvCalc.append("X")
    }

    private fun String.isOperator() = !this.isNumber() && !this.isNullOrBlank()

    private fun String.isNumber(): Boolean {
        var input = this?.trim()
        if ( !input.isNullOrEmpty() && input.toIntOrNull() != null ) {
            return true
        }
        return false
    }

}