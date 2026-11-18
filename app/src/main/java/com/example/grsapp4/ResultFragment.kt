package com.example.grsapp4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.grsapp4.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val score = arguments?.getInt("score") ?: 0
        val maxScore = arguments?.getInt("maxScore") ?: 0

        val (titleRes, descriptionRes) = when {
            score >= 7 -> R.string.result_expert_title   to R.string.result_expert_description
            score >= 4 -> R.string.result_good_title     to R.string.result_good_description
            else       -> R.string.result_beginner_title to R.string.result_beginner_description
        }

        binding.resultTitle.text = getString(titleRes)
        binding.resultDescription.text = getString(descriptionRes)
        binding.resultScore.text = getString(R.string.result_score_format, score, maxScore)

        binding.restartButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_quizFragment)
        }

        binding.logoutButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}