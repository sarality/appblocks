package com.sarality.app.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.sarality.app.common.collect.Lists;
import com.sarality.app.common.collect.Maps;
import com.sarality.app.common.collect.Sets;
import com.sarality.app.view.action.Action;
import com.sarality.app.view.action.TriggerType;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Base implementation of all types of dialog fragments.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class BaseDialogFragment extends DialogFragment {

  public static final Set<TriggerType> DIALOG_TRIGGER_TYPES = Sets.of(TriggerType.DIALOG_CANCEL);
  public static final Set<TriggerType> DIALOG_LIST_TRIGGER_TYPES = Sets.of(TriggerType.DIALOG_POSITIVE,
      TriggerType.DIALOG_NEGATIVE, TriggerType.DIALOG_NEUTRAL);

  private final Map<TriggerType, DialogAction> actionMap = Maps.empty();
  private final List<DialogAction> actionList = Lists.emptyList();

  private final Map<TriggerType, DialogButtonAction> listActionMap = Maps.empty();
  private final List<DialogButtonAction> listActionList = Lists.emptyList();

  /**
   * Register an Action for the Dialog.
   *
   * @param triggerType Event that triggers this Action.
   * @param action Action to be performed when event is triggered.
   */
  public void registerAction(TriggerType triggerType, Action<DialogActionContext> action) {
    assertValidAction(triggerType, DIALOG_TRIGGER_TYPES);
    DialogAction dialogAction = new DialogAction(action, triggerType);
    actionMap.put(triggerType, dialogAction);
    actionList.add(dialogAction);
  }

  public void registerAction(TriggerType triggerType, String label, Action<DialogButtonActionContext> action) {
    assertValidAction(triggerType, DIALOG_LIST_TRIGGER_TYPES);
    DialogButtonAction dialogAction = new DialogButtonAction(label, action, triggerType);
    listActionMap.put(triggerType, dialogAction);
    listActionList.add(dialogAction);
  }

  public void registerAction(TriggerType triggerType, int labelResourceId, Action<DialogButtonActionContext> action) {
    assertValidAction(triggerType, DIALOG_LIST_TRIGGER_TYPES);
    DialogButtonAction dialogAction = new DialogButtonAction(labelResourceId, action, triggerType);
    listActionMap.put(triggerType, dialogAction);
    listActionList.add(dialogAction);
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    setupActions(builder);
    setupButtonActions(builder);
    configure(builder);
    Dialog dialog = builder.create();
    configure(dialog);
    return dialog;
  }

  /**
   * Configure the properties of the Dialog.
   * <p/>
   * Defines properties of the dialog by calling methods like {@link #setCancelable(boolean)}.
   *
   * @param dialog Dialog that is being rendered.
   */
  protected abstract void configure(Dialog dialog);

  /**
   * Configure the Alert Dialog Builder.
   *
   * @param builder The Builder for the Alert Dialog.
   */
  protected abstract void configure(AlertDialog.Builder builder);

  /**
   * Setup actions for the Dialog
   */
  private void setupActions(AlertDialog.Builder builder) {
    for (DialogAction action : actionList) {
      action.setup(builder, action.getTriggerType());
    }
  }

  /**
   * Setup actions for the Dialog Buttons
   */
  private void setupButtonActions(AlertDialog.Builder builder) {
    for (DialogButtonAction action : listActionList) {
      action.setup(builder, action.getTriggerType());
    }
  }

  /**
   * Validate the Actions being set for Dialog
   */
  private void assertValidAction(TriggerType triggerType, Set<TriggerType> validTriggerTypes) {
    if (!validTriggerTypes.contains(triggerType)) {
      throw new IllegalArgumentException("Cannot register a Dialog Action with trigger " + triggerType +
          ". The action must of type " + validTriggerTypes);
    }
  }
}
