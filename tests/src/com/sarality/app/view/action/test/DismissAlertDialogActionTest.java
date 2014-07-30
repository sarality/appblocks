package com.sarality.app.view.action.test;

import static org.mockito.Mockito.mock;

import org.mockito.Mockito;

import com.sarality.app.view.action.DismissAlertDialogAction;
import com.sarality.app.view.action.TriggerType;
import com.sarality.app.view.dialog.AlertDialogComponent;

/**
 * Tests for {@link DismissAlertDialogAction}.
 * 
 * @author sunayna@ (Sunayna Uberoy)
 */
public class DismissAlertDialogActionTest extends BaseUnitTest {

  public void testDoAction() {
    @SuppressWarnings("unchecked")
    AlertDialogComponent<String> alertDialog = mock(AlertDialogComponent.class);
    DismissAlertDialogAction<String> dismissAction = new DismissAlertDialogAction<String>(1, TriggerType.CLICK, alertDialog);
    dismissAction.doAction(null, null, null);
    Mockito.verify(alertDialog, Mockito.times(1)).dismiss();
  }

  public void testDismissAlertDialogAction() {
    @SuppressWarnings("unchecked")
    AlertDialogComponent<String> alertDialog = mock(AlertDialogComponent.class);
    DismissAlertDialogAction<String> dismissAction = new DismissAlertDialogAction<String>(1, TriggerType.CLICK, alertDialog);
    assertNotNull(dismissAction);
  }
}
