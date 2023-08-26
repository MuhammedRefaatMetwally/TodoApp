package com.route.todosappc38online.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.todosappc38online.database.model.Task
import com.route.todosappc38online.databinding.ItemTodoBinding
import com.zerobranch.layout.SwipeLayout.SwipeActionsListener

class TodosListAdapter(private var todosList: List<Task>? = null , val listener : SwipeActionsListener) : Adapter<TodosListAdapter.TodosListViewHolder>() {

    var position : Int? = null
    inner class TodosListViewHolder(val binding : ItemTodoBinding) : ViewHolder(binding.root){
       fun bind(task : Task){
           binding.todoTitleText.text = task.title
           binding.descriptionItem.text = task.description
       }
   }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosListViewHolder {
        val itemBinding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TodosListViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return todosList?.size ?: 0
    }

    fun updateData(todosList: List<Task>?) {
        this.todosList = todosList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodosListViewHolder, position: Int) {
       holder.bind(todosList!![position])
        holder.binding.swipeLayoutX.setOnActionsListener(listener)
        this.position = position
    }




}