import styles from "./TodoCard.module.scss";

const TodoCard = () => {
  return (
    <div className={styles.TodoCard}>
      <div className={styles.checkbox}></div>
      <div className={styles.todoName}></div>
    </div>
  );
};

export default TodoCard;
