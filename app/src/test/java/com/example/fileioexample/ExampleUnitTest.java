package com.example.fileioexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.fileioexample.login.LoginModel;
import com.example.fileioexample.login.LoginPresenter;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    LoginActivity view;

    @Mock
    LoginModel model;

    /*@Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }*/

    /*
    @Test
    public void testPresenter(){
        /*** stubbing ***/
        //when(view.getUsername()).thenReturn("abc");
        //when(model.userExists("abc")).thenReturn(true);

        //MyPresenter presenter = new MyPresenter(model, view);
        //presenter.checkUsername();

        /*** verifying a method call with a specific value ***/
        //verify(view).displayMessage("user found");

        /*** verifying a method call with any value ***/
        //verify(view).displayMessage(anyString());

        /*** verifying a certain number of method calls ***/
        //verify(view, times(2)).displayMessage("user found");

        /*** Argument captors ***/
        //ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        //verify(view).displayMessage(captor.capture());
        //assertEquals(captor.getValue(), "user found");

        /*** Verifying order ***/
        //InOrder order = inOrder(model, view);
        //order.verify(model).userExists("abc");
        //order.verify(view).displayMessage("user found");
    //}

    @Test
    public void testCustomerLogin(){
        //Stubbing
        when(view.getUsername()).thenReturn("David");
        when(view.getPassword()).thenReturn("WuJinLong58");
        when(model.validateLogin("David", "WuJinLong58")).thenReturn(true);
        when(model.accountType("David")).thenReturn("customer");

        //Setup presenter
        LoginPresenter presenter = new LoginPresenter(model, view);
        presenter.login();

        //Verify that customerLogin was called
        verify(view).customerLogin();
    }

    @Test
    public void testOwnerLogin(){
        //Stubbing
        when(view.getUsername()).thenReturn("C9Tenz");
        when(view.getPassword()).thenReturn("Sentinels123");
        when(model.validateLogin("C9Tenz", "Sentinels123")).thenReturn(true);
        when(model.accountType("C9Tenz")).thenReturn("owner");

        //Setup presenter
        LoginPresenter presenter = new LoginPresenter(model, view);
        presenter.login();

        //Verify that ownerLogin was called
        verify(view).ownerLogin();
    }

    @Test
    public void testPasswordButNoUsername(){
        //Stubbing
        when(view.getUsername()).thenReturn("Nahweigo");
        when(view.getPassword()).thenReturn("alexPlayDespacito777");
        when(model.validateLogin("Nahweigo", "alexPlayDespacito777")).thenReturn(true);
        when(model.accountType("Nahweigo")).thenReturn("not found");

        //Setup presenter
        LoginPresenter presenter = new LoginPresenter(model, view);
        presenter.login();

        //Verify that account type couldn't be identified was called
        verify(view).displayMessage("Unable to identify account type");
    }

    @Test
    public void testIncorrectPassword(){
        //Stubbing
        when(view.getUsername()).thenReturn("DioBrando");
        when(view.getPassword()).thenReturn("ZaWarudo13");
        when(model.validateLogin("DioBrando", "ZaWarudo13")).thenReturn(false);

        //Setup presenter
        LoginPresenter presenter = new LoginPresenter(model, view);
        presenter.login();

        //Verify that password was incorrect
        verify(view).displayMessage("Invalid username or password");
    }

}