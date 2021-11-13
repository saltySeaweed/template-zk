package com.example.zktraining.controller;

import com.example.zktraining.dto.Student;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkmax.ui.util.Toast;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class HomeController {
//    @WireVariable
//    private ServiceA serviceA;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private List<Student> studentList;
    @Getter
    @Setter
    private Student student;
    @Init
    public void init(){
        title = "Hello word!";
        initObject();
    }
    private void initObject(){
        studentList = new ArrayList<>();
        studentList.add(new Student(1, "Phúc", "HN"));
        studentList.add(new Student( 2,"Dưỡng", "DCM"));
        studentList.add(new Student(3, "Long", "HF"));
        studentList.add(new Student(4, "Hùng", "FFF"));
        student = new Student();
    }

    @Command
    @NotifyChange({"title"})
    public void changeTitle(){
        title = "Tao Thich Thế";
    }

    @Command
    @NotifyChange({"student"})
    public void saveStudent(){
        if (student.getId() == null){
            student.setId(studentList.size()+1);
            studentList.add(student);
            BindUtils.postNotifyChange(null, null, studentList, "*");
        }
        student = new Student();
    }




    @Command
    @NotifyChange("student")
    public void editStudent(@BindingParam("item")Student item){
        student = item;
    }
    @Command
    public void confirmDelete(@BindingParam("item")Student item){
        Messagebox.show("Mày muốn xóa", "Thông báo xóa", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onOK")) {
                    studentList.remove(item);
                    Toast.show("Xóa Thành công !");
                    BindUtils.postNotifyChange(null, null, studentList, "*");
                } else if (evt.getName().equals("onCancel")) {
                    Toast.show("không xóa nữa !");
                } else {
                    Toast.show("Data Saved !");
                }
            }
        });
    }

    @Command
    public void changeName(){
        studentList.forEach(item -> {
            item.setName(item.getName() + " 1");
            BindUtils.postNotifyChange(null, null, item, "name");
        });
    }

    @Command
    public void openFormAddNew(){
        Map<String, Object> arg = new HashMap<>();
        arg.put("mode", "insert");
        Window window = (Window) Executions.createComponents("form.zul", null, arg);
        window.doModal();
    }

    @GlobalCommand
    @NotifyChange("studentList")
    public void onLoadDataFormInsertStudent(@BindingParam("item")Student student){
        studentList.add(student);
    }

}
