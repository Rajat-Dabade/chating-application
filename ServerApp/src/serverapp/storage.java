/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapp;

/**
 *
 * @author matrix
 */
public class storage{
    String source;
    String msg;
    
    void setSource(String source)
    {
        this.source = source;
    }
    void setMessage(String msg)
    {
        this.msg = msg;
    }
    
    String getSource()
    {
        return this.source;
    }
    String getMessage()
    {
        return this.msg;
    }
}
