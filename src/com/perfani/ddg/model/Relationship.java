package com.perfani.ddg.model;

public class Relationship
{
    private Entity from;
    
    private String nFrom;
    
    private Entity to;
    
    private String nTo;

    /**
     * @return the from
     */
    public Entity getFrom()
    {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(Entity from)
    {
        this.from = from;
    }

    /**
     * @return the nFrom
     */
    public String getnFrom()
    {
        return nFrom;
    }

    /**
     * @param nFrom the nFrom to set
     */
    public void setnFrom(String nFrom)
    {
        this.nFrom = nFrom;
    }

    /**
     * @return the to
     */
    public Entity getTo()
    {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(Entity to)
    {
        this.to = to;
    }

    /**
     * @return the nTo
     */
    public String getnTo()
    {
        return nTo;
    }

    /**
     * @param nTo the nTo to set
     */
    public void setnTo(String nTo)
    {
        this.nTo = nTo;
    }
}
