package com.example.grsapp4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.grsapp4.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private lateinit var questions: List<Question>
    private var currentIndex = 0
    private var score = 0
    private var answered = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbHelper = QuizDbHelper(requireContext())
        questions = dbHelper.getAllQuestions()
        dbHelper.close()

        if (questions.isEmpty()) {
            binding.questionText.text = getString(R.string.no_questions)
            disableOptions()
            return
        }

        showQuestion()
    }

    private fun showQuestion() {
        answered = false

        val question = questions[currentIndex]

        binding.questionText.text = getString(
            R.string.question_format,
            currentIndex + 1,
            questions.size,
            question.text
        )

        val buttons = listOf(
            binding.option1Button,
            binding.option2Button,
            binding.option3Button,
            binding.option4Button
        )

        buttons.forEachIndexed { index, button ->
            val option = question.options.getOrNull(index)
            button.text = option ?: ""
            button.isEnabled = option != null
            button.setOnClickListener {
                if (option != null) onAnswerSelected(index)
            }
        }
    }

    private fun onAnswerSelected(selectedIndex: Int) {
        if (answered) return
        answered = true

        if (selectedIndex == questions[currentIndex].correctIndex) {
            score++
        }

        currentIndex++

        if (currentIndex >= questions.size) {
            findNavController().navigate(
                R.id.action_quizFragment_to_resultFragment,
                bundleOf(
                    "score" to score,
                    "total" to questions.size
                )
            )
        } else {
            showQuestion()
        }
    }

    private fun disableOptions() {
        binding.option1Button.isEnabled = false
        binding.option2Button.isEnabled = false
        binding.option3Button.isEnabled = false
        binding.option4Button.isEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}