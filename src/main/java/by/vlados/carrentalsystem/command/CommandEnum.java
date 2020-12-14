package by.vlados.carrentalsystem.command;

import by.vlados.carrentalsystem.command.admin.*;
import by.vlados.carrentalsystem.command.auth.*;
import by.vlados.carrentalsystem.command.user.*;

/**
 *
 * @author vlados
 * Enum of all commands, constructor initiates chosen command's execution
 */
public enum CommandEnum {

   
    /**
     * new user registration command
     */
    REGISTER {
                {
                    this.command = new RegisterCommand();
                }
            },

    /**
     * deleting application if user chooses not to pay for it
     */
    DELETEAPPLICATION {
                {
                    this.command = new DeleteOrderCommand();
                }
            },
    
    /**
     * deleting application if user chooses not to pay for it
     */
    DELETEUNPAID {
                {
                    this.command = new DeleteOrderACommand();
                }
            },
    
    /**
     * deleting application if user chooses not to pay for it
     */
    REPAIRED {
                {
                    this.command = new RepairedCommand();
                }
            },

    /**
     * administrator registers that car is returned without any damage
     */
    RETURN {
                {
                    this.command = new ReturnCommand();
                }
            },
      /**
     * administrator registers that car is returned without any damage
     */
    DENIED {
                {
                    this.command = new DeniedCommand();
                }
            },
      /**
     * administrator registers that car is returned without any damage
     */
    PAID {
                {
                    this.command = new PaidCommand();
                }
            },

    /**
     * administrator registers that car is returned with damage
     */
    RETURNDAMAGE {
                {
                    this.command = new ReturnDamageCommand();
                }
            },

  

    /**
     * getting user back to cars.jsp - delete later
     */
    BACKCARSU {
                {
                    this.command = new CarsRedirectCommand();
                }
            },

    /**
     * getting guest to the register.jsp
     */
    REGISTERREDIRECT {
                {
                    this.command = new RegisterRedirectCommand();
                }
            },

    /**
     * getting user to account.jsp
     */
    ACCOUNT {
                {
                    this.command = new AccountCommand();
                }
            },

    /**
     * getting user to logout.jsp
     */
    LOGOUTREDIRECT {
                {
                    this.command = new LogoutRedirectCommand();
                }
            },

    /**
     * getting admin to logouta.jsp
     */
    LOGOUTAREDIRECT {
                {
                    this.command = new LogoutARedicrectCommand();
                }
            },

    /**
     * changing user's password
     */
    CHANGEPASSWORDCONF {
                {
                    this.command = new ChangePasswordConfCommand();
                }
            },

    /**
     * getting user to changepassword.jsp
     */
    CHANGEPASSWORD {
                {
                    this.command = new ChangePasswordCommand();
                }
            },
    
    /**
     * getting user to changepassword.jsp
     */
    CHANGEEMAIL {
                {
                    this.command = new ChangeEmailCommand();
                }
            },

    /**
     * getting user to main.jsp
     */
    MAINREDIRECT {
                {
                    this.command = new MainRedirectCommand();
                }
            },

    /**
     * getting admin to page with new applications
     */
    NEWORDERS {
                {
                    this.command = new NewOrdersCommand();
                }
            },

    /**
     *
     */
    CARINFO {
                {
                    this.command = new CarInfoCommand();
                }
            },

    /**
     *
     */
    MAINADMREDIRECT {
                {
                    this.command = new MainAdmCommand();
                }
            },

    /**
     *
     */
    PAIDORDERS {
                {
                    this.command = new PaidOrdersCommand();
                }
            },
    /**
     *
     */
    UNPAIDORDERS {
                {
                    this.command = new UnpaidOrdersCommand();
                }
            },

    /**
     *
     */
    ARCHIVEORDERS {
                {
                    this.command = new ArchiveOrdersCommand();
                }
            },

    /**
     *
     */
    DEFINEORDER {
                {
                    this.command = new DefineOrderCommand();
                }
            },

    /**
     *
     */
    CHOOSEREDIRECT {
                {
                    this.command = new OrderReCommand();
                }
            },

    /**
     *
     */
    CARSREDIRECTADMIN {
                {
                    this.command = new CarsAdmCommand();
                }
            },

    /**
     *
     */
    CARSREDIRECT {
                {
                    this.command = new CarsRedirectCommand();
                }
            },

    /**
     *
     */
    CONTACTSREDIRECT {
                {
                    this.command = new ContactsRedirectCommand();
                }
            },

    /**
     *
     */
    BASKET {
                {
                    this.command = new BasketCommand();
                }
            },

    /**
     *
     */
    LOGIN {
                {
                    this.command = new LoginCommand();
                }
            },

    /**
     *
     */
    RU {
                {
                    this.command = new LanguageCommand();
                }
            },

    /**
     *
     */
    EN {
                {
                    this.command = new LanguageCommand();
                }
            },

    /**
     *
     */
    ORDERRE {
                {
                    this.command = new OrderReCommand();
                }
            },

    /**
     *
     */
    USERS {
                {
                    this.command = new UsersCommand();
                }
            },

    /**
     *
     */
    USERSW {
                {
                    this.command = new UsersWCommand();
                }
            },

    

    /**
     *
     */
    DELETEUSER {
                {
                    this.command = new DeleteUserCommand();
                }
            },
        /**
     *
     */
    REPAIRBILLS {
                {
                    this.command = new RepairBillsCommand();
                }
            },

    

    

    /**
     *
     */
    ALLCARS {
                {
                    this.command = new AllCarsCommand();
                }
            },

    /**
     *
     */
    ADDNEWCAR {
                {
                    this.command = new AddNewCarCommand();
                }
            },

    /**
     *
     */
    CONFIRM {
                {
                    this.command = new ConfirmCommand();
                }
            },

    /**
     *
     */
    DENY {
                {
                    this.command = new DenyCommand();
                }
            },

    /**
     *
     */
    LOGOUT {
                {
                    this.command = new LogoutCommand();
                }
            },

    /**
     *
     */
    CHANGECARNAME {
                {
                    this.command = new ChangeCarNameCommand();
                }
            },

    /**
     *
     */
    CHANGECARIMAGE {
                {
                    this.command = new ChangeCarImageCommand();
                }
            },
    /**
     *
     */
    CHANGEACTIVE {
                {
                    this.command = new ChangeActiveCommand();
                }
            },

    /**
     *
     */
    CHANGECARPRICE {
                {
                    this.command = new ChangeCarPriceCommand();
                }
            },

    /**
     *
     */
    DELETECAR {
                {
                    this.command = new DeleteCarCommand();
                }
            },
    RETURNINDEX {
                {
                    this.command = new ReturnIndexCommand();
                }
            },
    

    /**
     *
     */
    PAY {
                {
                    this.command = new PayCommand();
                }
            },

    /**
     *
     */
    NEWCAR {
                {
                    this.command = new NewCarCommand();
                }
            };

    ActionCommand command;

    /**
     *
     * @return
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }

}
