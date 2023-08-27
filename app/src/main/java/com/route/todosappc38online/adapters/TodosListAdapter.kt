package com.route.todosappc38online.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.todosappc38online.R
import com.route.todosappc38online.database.model.Task
import com.route.todosappc38online.databinding.ItemTodoBinding
import com.zerobranch.layout.SwipeLayout.SwipeActionsListener

class TodosListAdapter(private var todosList: List<Task>? = null , val listener : SwipeActionsListener,val taskListener : OnTaskClick) : Adapter<TodosListAdapter.TodosListViewHolder>() {

    var position : Int? = null

    var onDoneClick : OnDoneClick? = null

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
        holder.binding.cardItemTask.setOnLongClickListener{
            taskListener.onClick(task = todosList!![position],position)
            true
        }
        if(todosList!![position].isDone == true){
            holder.binding.verticalLine.setBackgroundColor(Color.GREEN)
            holder.binding.todoTitleText.setTextColor(Color.GREEN)
            holder.binding.doneBtn.setBackgroundResource(R.drawable.done)
            holder.binding.doneBtn.setImageResource(0)
        }

        holder.binding.doneBtn.setOnClickListener {

            if(todosList!![position].isDone == false){
                holder.binding.verticalLine.setBackgroundColor(Color.GREEN)
                holder.binding.todoTitleText.setTextColor(Color.GREEN)
                holder.binding.doneBtn.setBackgroundResource(R.drawable.done)
                holder.binding.doneBtn.setImageResource(0)
                todosList!![position].isDone = true
            }

            onDoneClick?.updateDatabase(todosList!![position],position)

        }
    }

    fun interface OnTaskClick{
        fun onClick(task: Task,position: Int)
    }

    fun interface OnDoneClick{
        fun updateDatabase(task: Task,position: Int)
    }





}