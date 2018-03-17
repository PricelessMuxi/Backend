package br.com.priceless.someidea.service.exception;

@SuppressWarnings("serial")
public class NotEnoughPointsToBeRedeemedException extends Exception {

	public NotEnoughPointsToBeRedeemedException(long pointsBalance) {
		super("There are only " + pointsBalance + " to redeem");
	}
}
