//アプリ層
package com.todo.todoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.todo.todoapp.entity.Todo;
import com.todo.todoapp.mapper.TodoMapper;


@Controller
@SessionAttributes(names = "name")
public class TodoController {
    public String order_flg ="0"; //0:昇順、1:降順
    @Autowired
    TodoMapper todoMapper;

    @ModelAttribute("name")
    public String setName(String name) {
        return name;
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("todolist")
    public String todolist(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        if (order_flg == "0"){
            List<Todo> list = todoMapper.selectIncomplete_asc();
            List<Todo> doneList = todoMapper.selectComplete_asc();
            model.addAttribute("todos",list);
            model.addAttribute("doneTodos",doneList);
        }else{
            List<Todo> list = todoMapper.selectIncomplete_desc();
            List<Todo> doneList = todoMapper.selectComplete_desc();
            model.addAttribute("todos",list);
            model.addAttribute("doneTodos",doneList);
        }
        return "todolist";
 }

    @GetMapping("todolist")
        public String todolist(Model model){
            if (order_flg == "0"){
                List<Todo> list = todoMapper.selectIncomplete_asc();
                List<Todo> doneList = todoMapper.selectComplete_asc();
                model.addAttribute("todos",list);
                model.addAttribute("doneTodos",doneList);
            }else{
                List<Todo> list = todoMapper.selectIncomplete_desc();
                List<Todo> doneList = todoMapper.selectComplete_desc();
                model.addAttribute("todos",list);
                model.addAttribute("doneTodos",doneList);
            }
        return "todolist";
    }

    @RequestMapping(value="todolist/add") //新規タスク追加
    public String add(Todo todo) {
        todoMapper.add(todo);
        return "redirect:/todolist";
    }

    @RequestMapping(value="todolist/update") //マイタスクの修正
    public String update(Todo todo) {
        todoMapper.update(todo);
        return "redirect:/todolist";
    }

    @RequestMapping(value="todolist/delete") //完了済みのデータ削除
    public String delete(){
        todoMapper.delete();
        return "redirect:/todolist";
    }
    @RequestMapping(value="todolist/desc") //降順処理（マイタスク、完了済み）
    public String desc(){
        order_flg = "1";
        return "redirect:/todolist";
    }
    @RequestMapping(value="todolist/asc") //昇順処理（マイタスク、完了済み）
    public String asc(){
        order_flg = "0";
        return "redirect:/todolist";
    }
}
