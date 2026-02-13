package kh.edu.rupp.demoapp.service;

import java.util.List;
import kh.edu.rupp.demoapp.model.Task;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("todos") // This is the path that will be appended to the base URL
    Call<List<Task>> getTasks();
}