package skhu.ht.hotthink.api.idea.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import skhu.ht.hotthink.api.domain.Board;
import skhu.ht.hotthink.api.idea.model.IdeaPagination;
import skhu.ht.hotthink.api.idea.model.Option;
import skhu.ht.hotthink.api.idea.model.Pagination;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Option[] searchBy = { new Option(0,"검색없음"), new Option(1,"글쓴이"),
                          new Option(2,"제목"), new Option(3,"내용")};
    Option[] orderBy = { new Option(0,"최근 글"), new Option(1, "오래된 글"),
                         new Option(2, "글쓴이")};
    Sort[] sort = { new Sort(Sort.Direction.DESC, "id"),
             new Sort(Sort.Direction.ASC, "id"),
             new Sort(Sort.Direction.ASC, "user_name")};

    default List<Board> findAll(IdeaPagination pagination){
        Pageable pageable = PageRequest.of(pagination.getPage()-1, pagination.getSize(), sort[pagination.getOrderBy()]);
        Page<Board> page;
        String category = pagination.getCategory();
        String searchText = pagination.getSearchText();
        switch(pagination.getSearchBy()){
            case 1:
                page = this.findByCategoryAndUserAndBoardType(category,searchText, pageable);
                break;
            case 2:
                page = this.findByCategoryAndTitleAndBoardType(category, searchText, pageable);
                break;
            case 3:
                page = this.findByCategoryAndContentsAndBoardType(category, searchText, pageable);
                break;
            default:
                page = this.findByCategoryAndBoardType(category, pageable);
        }
        return page.getContent();
    }



    default List<Board> findAll(Pagination pagination){
        Pageable pageable = PageRequest.of(pagination.getPage()-1, pagination.getSize(), sort[pagination.getOrderBy()]);
        Page<Board> page;
        String category = pagination.getCategory();
        String searchText = pagination.getSearchText();
        switch(pagination.getSearchBy()){
            case 1:
                page = this.findByCategoryAndUserAndBoardType(category,searchText, pageable);
                break;
            case 2:
                page = this.findByCategoryAndTitleAndBoardType(category, searchText, pageable);
                break;
            case 3:
                page = this.findByCategoryAndContentsAndBoardType(category, searchText, pageable);
                break;
            default:
                page = this.findByCategoryAndBoardType(category, pageable);
        }
        return page.getContent();
    }

    @Query(value = "SELECT getBD_Seq(?1)", nativeQuery = true)
    Long findBoardSeq(String category);

    @Query(value = "CALL likeFree(?1)", nativeQuery = true)
    void likeFree(Long freeId);

    //@Query(value = "SELECT BD.BD_SEQ FROM TB_BOARD BD INNER JOIN CATEGORY CT ON BD.CT_CODE = CT.CODE WHERE FR.SEQ = ?1 AND CT.CATEGORY = ?2", nativeQuery = true)
    //Long findBdSeq(Long seq, String category);

    Page<Board> findByCategoryAndContentsAndBoardType(String category, String Contents, Pageable pageable);
    Page<Board> findByCategoryAndTitleAndBoardType(String category, String Title, Pageable pageable);
    Page<Board> findByCategoryAndUserAndBoardType(String category, String User, Pageable pageable);
    Page<Board> findByCategoryAndBoardType(String category, Pageable pageable);
    Board findBoardByBdSeq(Long bdSeq);
    //Free findFreeBySeqAndCategory(Long seq, Category category);
}