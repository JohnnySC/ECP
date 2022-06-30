package com.github.johnnysc.ecp.presentation.messages

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.coremvvm.presentation.BackPress
import com.github.johnnysc.ecp.R
import com.github.johnnysc.ecp.presentation.messages.adapter.MessageAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MessagesFragment : BackPress.Fragment<MessagesUI, MessagesViewModel>() {

    override val layoutResId = R.layout.fragment_main

    override fun viewModelClass(): Class<MessagesViewModel> = MessagesViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val messagesRecyclerView = view.findViewById<RecyclerView>(R.id.messagesRecyclerView)
        val messageAdapter = MessageAdapter.Base()
        messagesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, true)
        messagesRecyclerView.adapter = messageAdapter
        val messageInput = view.findViewById<EditText>(R.id.messageEditText)
        val sendMessageButton = view.findViewById<FloatingActionButton>(R.id.sendMessageButton)
        sendMessageButton.setOnClickListener {
            val message = messageInput.text.toString()

                //todo trim message handle in view model



        }
        viewModel.observe(this) { messagesUI ->
            messagesUI.map(messageAdapter)
        }

    }


}