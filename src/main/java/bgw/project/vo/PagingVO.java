package bgw.project.vo;

import lombok.Data;

import java.awt.print.Pageable;

@Data
public class PagingVO {
    // 현재페이지, 시작페이지, 끝페이지, 게시글 총 갯수, 페이지당 글 갯수, 마지막페이지, SQL쿼리에 쓸 start, end
    private int nowPage=1;
    private int startPage;
    private int endPage;
    private int total=10;
    private int perPage=10;
    private int lastPage;
    private int start;
    private int end;
    private int cntPage = 5;
    private String name;

    public PagingVO(){

    }
    public PagingVO total(int total){
        this.total = total;
        return this;
    }
    public PagingVO perPage(int perPage){
        this.perPage = perPage;
        return this;
    }

    public PagingVO nowPage(int nowPage){
        this.nowPage = nowPage;
        return this;
    }
    public void calcResult(){
        calcLastPage(getTotal(), getPerPage());
        calcStartEndPage(getNowPage(), cntPage);
        calcStartEnd(getNowPage(), getPerPage());
    }




    // 제일 마지막 페이지 계산
    public void calcLastPage(int total, int cntPerPage) {
        setLastPage((int) Math.ceil((double)total / (double)cntPerPage));
    }
    // 시작, 끝 페이지 계산
    public void calcStartEndPage(int nowPage, int cntPage) {
        setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
        if (getLastPage() < getEndPage()) {
            setEndPage(getLastPage());
        }
        setStartPage(getEndPage() - cntPage + 1);
        if (getStartPage() < 1) {
            setStartPage(1);
        }
    }
    // DB 쿼리에서 사용할 start, end값 계산
    public void calcStartEnd(int nowPage, int cntPerPage) {
        setEnd(nowPage * cntPerPage);
        setStart(getEnd() - cntPerPage + 1);
    }


}
