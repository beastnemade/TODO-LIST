package App;

public class Queue {
    int rear;
    int front;
    int size;
    Task tasks[];
    Queue(int s){
        tasks=new Task[s];
        rear=front=-1;
        size=s;
    }

    void enqueue(Task t){
        if(rear==size-1){
            System.out.println("Queue is Full Wait a While");
        }
        else{
            if(front==-1){
                front=0;
            }
            rear=rear+1;
            tasks[rear]=t;
            System.out.println("Order Proceeded for Making");
        }
    }

    Task dequeue(){
        if(front==-1){
            System.out.println("Queue is Empty");
            return null;
        }
        else{
            Task o;
            o=tasks[front];
            if(front==rear){
                front=rear=-1;
            }
            else{
                front=front+1;
            }
            System.out.println("Order with dispatched for Delivery");
            return o;
        }
    }

    void print(){
        try{
            if(front==-1){
                System.out.println("Queue is Empty");
            }
            else{
                for(int i=front;i<=rear;i++){
                    System.out.println(tasks[i].toString());
                }
            }
        }
        catch(Exception e){
            System.out.println("Queue is Empty");
        }
    }
}
