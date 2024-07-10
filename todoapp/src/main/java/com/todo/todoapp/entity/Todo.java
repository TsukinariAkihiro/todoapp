//インフラ層
package com.todo.todoapp.entity;

//import java.sql.Date;

import lombok.Data;

@Data //@Repositoryより便利。getterやsetterが不要になる。
public class Todo {                 //DTOクラス ※データを転送するためのクラス

    private long id;
    private String title;
    private int done_flg;
    private String time_limit;
}