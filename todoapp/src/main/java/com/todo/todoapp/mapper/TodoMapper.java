package com.todo.todoapp.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.todo.todoapp.entity.Todo;

@Mapper
public interface TodoMapper {              //DAOクラス　※データベースなどのデータストアを操作する

    //デフォルト昇順
    public List<Todo> selectIncomplete_asc(); //⑤未完了のデータを取得（SELECT）
    public List<Todo> selectComplete_asc();   //⑥完了のデータを取得（SELECT）
    //降順
    public List<Todo> selectIncomplete_desc();
    public List<Todo> selectComplete_desc();

    public void add(Todo todo);    //②画面からタスク（データ）を追加してDBに格納（INSERT)
    public void update(Todo todo); //③画面からタスク（データ）を修正してDBに格納（UPDATE）
    public void delete();          //⑦画面から完了済みのタスク（データ）を削除する(DELETE)
}
