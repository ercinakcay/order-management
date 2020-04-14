package com.ea.ordermanagementapi.context;

public class ContextManager
{
    private static final ThreadLocal<Context> threadLocalContext = new ThreadLocal<>();
        
    public static Context get()
    {
        return threadLocalContext.get();
    }

    public static void set(Context ctx)
    {
        threadLocalContext.set(ctx);
    }
    
    public static void clear()
    {
        threadLocalContext.remove();
    }
}
