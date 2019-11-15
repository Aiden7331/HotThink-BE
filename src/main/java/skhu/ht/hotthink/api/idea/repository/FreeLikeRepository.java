package skhu.ht.hotthink.api.idea.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import skhu.ht.hotthink.api.domain.Like;

@Repository
public interface FreeLikeRepository extends JpaRepository<Like, Integer> {
}