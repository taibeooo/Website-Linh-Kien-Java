package Midterm.webgk.Repository;
import Midterm.webgk.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface RegisterRepository extends JpaRepository<User,Integer>{
    @Query("select v from User v where v.Email   = :inName")
    User checkUser(@Param("inName")String inNa);

}
