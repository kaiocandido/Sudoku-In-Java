package Model;

import java.util.Collection;
import java.util.List;

import static Model.GameStatusEnum.COMPLETE;
import static Model.GameStatusEnum.INCOMPLETE;
import static Model.GameStatusEnum.NO_STARTED;
import static java.util.Objects.isNull;

public class Board {
    private final List<List<Space>> spaces;

    public Board(List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public GameStatusEnum getStatus(){
        if(spaces.stream().flatMap(Collection::stream).anyMatch(s -> !s.isFixed() && s.getActual() == null)){
            return NO_STARTED;
        }

        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getActual())) ? INCOMPLETE : COMPLETE;
    }

    public  boolean haError(){
        if (getStatus() == NO_STARTED){
            return  false;
        }

        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getActual()) && s.getActual().equals(s.getExpected()));
    }

    public boolean changeValue(final int col, final int row, final int value){
        var space = spaces.get(col).get(row);
        if(space.isFixed()){
            return false;
        }

        space.setActual(value);

        return true;
    }

    public boolean clearValue(final int col, final int row){
        var space = spaces.get(col).get(row);
        if(space.isFixed()){
            return false;
        }

        space.clearSpace();

        return true;
    }

    public void reset(){
        spaces.forEach(c -> c.forEach(Space::clearSpace));
    }

    public boolean gameFineshed(){
        return  haError() && getStatus().equals(COMPLETE);
    }
}
