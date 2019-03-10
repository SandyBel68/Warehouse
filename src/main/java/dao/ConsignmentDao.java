package dao;

import entities.Consignment;

import java.util.List;

public interface ConsignmentDao {

    void addConsignment(Consignment con);

    void changeStatus(int idConsignment, String status);

    Consignment getById (int idConsignment);

    List<Consignment> getAll ();

}
