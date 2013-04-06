
/**
 * Interface for hanging entities (paintings, item frames, etc)
 *
 * @author gregthegeek
 *
 */
public class HangingEntity extends BaseEntity {

    public enum Position {
        /**
         * Faces south and is located on the block face
         */
        SOUTH_FACE,
        /**
         * Faces west and is located on the block face
         */
        WEST_FACE,
        /**
         * Faces north and is located on the block face
         */
        NORTH_FACE,
        /**
         * Faces east and is located on the block face
         */
        EAST_FACE,
        /**
         * Faces north and is located in the center of the block
         */
        NORTH_CENTER,
        /**
         * Faces west and is located in the center of the block
         */
        WEST_CENTER,
        /**
         * Faces south and is located in the center of the block
         */
        SOUTH_CENTER,
        /**
         * Faces east and is located in the center of the block
         */
        EAST_CENTER;
    }

    /**
     * Creates a new hanging entity wrapper
     *
     * @param entity The hanging entity to wrap
     */
    public HangingEntity(OEntityHanging entity) {
        super(entity);
    }

    /**
     * Places this entity in its world
     *
     */
    public void place() {
        getEntity().q.d(getEntity());
    }

    @Override
    public OEntityHanging getEntity() {
        return (OEntityHanging) entity;
    }

    /**
     * Returns the position of this hanging entity around its block
     *
     * @return
     */
    public Position getPosition() {
        return Position.values()[getEntity().a];
    }

    /**
     * Sets the position of this hanging entity around its block
     *
     * @param position The position to set it to
     */
    public void setPosition(Position position) {
        getEntity().a(position.ordinal());
    }
}
